import NotFound from "../../components/NotFound";
import renderer from 'react-test-renderer';

it('Renders <NotFound/> correctly', () => {
    const obj = renderer
        .create(<NotFound/>)
        .toJSON();

    expect(obj).toMatchSnapshot();
});